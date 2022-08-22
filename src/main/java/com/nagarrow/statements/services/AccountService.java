package com.nagarrow.statements.services;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.dto.StatementRecord;

import java.util.List;

public interface AccountService {
    List<StatementRecord> searchStatements(StatementFilter filter);
}
