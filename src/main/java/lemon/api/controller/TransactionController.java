package lemon.api.controller;

import lemon.api.dto.TransactionDto;
import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.Transaction;
import lemon.api.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

  @Autowired
  private ITransactionService transactionService;

  @GetMapping("/transaction")
  public ResponseEntity<List<Transaction>> findByUser(
          @RequestParam Long userId,
          @RequestParam(required = false) String type ,
          @RequestParam(required = false) String wallet,
          @RequestParam(defaultValue = "100") int limit,
          @RequestParam(defaultValue = "0") int offset
  )
      throws ResourceNotFoundException {
    Pageable pageable = PageRequest.of(offset, limit);
    List<Transaction> transactions;

    if (type != null && wallet != null) {
      transactions = transactionService.findByUserAndTypeAndWallet(userId, type, wallet, pageable);
    } else if (type != null) {
      transactions = transactionService.findByUserAndType(userId, type, pageable);
    } else if (wallet != null) {
      transactions = transactionService.findByUserAndWallet(userId, wallet, pageable);
    } else {
      transactions = transactionService.findByUser(userId, pageable);
    }

    return ResponseEntity.ok().body(transactions);
  }

  @PostMapping("/transaction/create")
  public ResponseEntity<TransactionDto> createTransaction(@Valid @RequestBody Transaction transaction)
      throws ResourceNotFoundException, Exception {
    Transaction transactionSaved = transactionService.createTransaction(transaction);
    return ResponseEntity.ok(new TransactionDto(transactionSaved));
  }
}
