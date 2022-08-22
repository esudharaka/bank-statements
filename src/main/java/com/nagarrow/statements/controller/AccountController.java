package com.nagarrow.statements.controller;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.dto.StatementRecord;
import com.nagarrow.statements.security.UserRole;
import com.nagarrow.statements.services.AccountService;
import com.nagarrow.statements.util.SecurityUtils;
import com.nagarrow.statements.util.StatementRequestValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/statements/v1/")
public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    public AccountController(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }
    private final AccountService accountService;

    @PostMapping("")
    List<StatementRecord> generateStatement(@Valid @RequestBody StatementFilter filter) {
        LOGGER.info("Account Statement generation request received. {}", filter);
        final UserRole userRole = SecurityUtils.getUserRole();
        final StatementFilter updatedSearchFilter = StatementRequestValidator.validateRequestFilter(filter, userRole);
        return accountService.searchStatements(updatedSearchFilter);
    }

}
