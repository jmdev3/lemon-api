package lemon.api.controller;

import lemon.api.dto.TransactionDto;
import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.Transaction;
import lemon.api.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

  @Autowired
  private ITransactionService transactionService;

  @GetMapping("/transaction/{userId}")
  public ResponseEntity<List<Transaction>> findByUser(@PathVariable(value = "userId") Long userId)
      throws ResourceNotFoundException {
    List<Transaction> transactions = transactionService.findByUser(userId);
    return ResponseEntity.ok().body(transactions);
  }

  @PostMapping("/transaction/create")
  public ResponseEntity<TransactionDto> createTransaction(@Valid @RequestBody Transaction transaction)
      throws ResourceNotFoundException, Exception {
    Transaction transactionSaved = transactionService.createTransaction(transaction);
    return ResponseEntity.ok(new TransactionDto(transactionSaved));
  }
}
