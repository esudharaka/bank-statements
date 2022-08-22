package com.nagarrow.statements.repository;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.dto.StatementRecord;
import com.nagarrow.statements.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository {

    Optional<Account> findAccount(StatementFilter filter);
    List<StatementRecord> searchStatements(StatementFilter filter);
}
