# Cross Border Gateway Test Cases

## Overview
This document outlines comprehensive test cases for the Cross Border Gateway project that handles foreign exchange transfers between banks and gateways. The system facilitates international transfers from source SAP (State Bank of India) to destination SAP (State Bank of Japan) via intermediate gateways.

## System Architecture Components
1. **Source SAP**: State Bank of India (SBI)
2. **Source Gateway**: Gateway connecting SBI to the international network
3. **Destination Gateway**: Gateway connecting the international network to SBJ
4. **Destination SAP**: State Bank of Japan (SBJ)
5. **Currency Onboarding Module**: Handles currency, country, and exchange rate configurations
6. **Transfer Application**: Manages FOR (Outward) and FIR (Inward) transfers

## Test Case Categories

### 1. Positive Test Cases

#### 1.1 Currency Conversion Tests
- TC001: Successful currency conversion from INR to JPY
- TC002: Successful currency conversion with valid exchange rates
- TC003: Currency conversion with multiple currencies
- TC004: Currency conversion with standard fees applied
- TC005: Currency conversion with special promotional rates

#### 1.2 Gateway-to-Gateway Transfer Tests
- TC006: Successful transfer from source gateway to destination gateway
- TC007: Transfer with valid routing information
- TC008: Transfer with proper authentication tokens
- TC009: Transfer with correct timestamp validation
- TC010: Transfer with proper transaction ID generation

#### 1.3 Bank-to-Bank Transfer Tests
- TC011: Successful transfer from SBI to SBJ
- TC012: Transfer with valid account information
- TC013: Transfer with proper beneficiary details
- TC014: Transfer with correct amount validation
- TC015: Transfer with proper fee calculation

### 2. Negative Test Cases

#### 2.1 Currency Conversion Negative Tests
- TC016: Currency conversion with invalid source currency
- TC017: Currency conversion with invalid target currency
- TC018: Currency conversion with zero amount
- TC019: Currency conversion with negative amount
- TC020: Currency conversion with unsupported currency pair
- TC021: Currency conversion with insufficient funds
- TC022: Currency conversion with expired exchange rates

#### 2.2 Gateway-to-Gateway Negative Tests
- TC023: Transfer with invalid gateway credentials
- TC024: Transfer with expired authentication tokens
- TC025: Transfer with invalid routing information
- TC026: Transfer with mismatched timestamps
- TC027: Transfer with duplicate transaction IDs
- TC028: Transfer with unauthorized gateway access

#### 2.3 Bank-to-Bank Negative Tests
- TC029: Transfer with invalid source account
- TC030: Transfer with invalid destination account
- TC031: Transfer with insufficient balance
- TC032: Transfer with invalid beneficiary details
- TC033: Transfer with incorrect amount format
- TC034: Transfer with blocked account

### 3. Failure Test Cases

#### 3.1 System Failure Tests
- TC035: Transfer fails due to gateway unavailability
- TC036: Transfer fails due to database connection issues
- TC037: Transfer fails due to timeout during currency conversion
- TC038: Transfer fails due to network connectivity issues
- TC039: Transfer fails due to invalid exchange rate service

#### 3.2 Business Logic Failure Tests
- TC040: Transfer fails due to exceeding daily limits
- TC041: Transfer fails due to exceeding monthly limits
- TC042: Transfer fails due to exceeding transaction limits
- TC043: Transfer fails due to compliance violation
- TC044: Transfer fails due to regulatory restrictions

### 4. Fail-Safe and Recovery Scenarios

#### 4.1 Transaction Recovery Tests
- TC045: Transaction rollback on system failure
- TC046: Transaction retry mechanism after temporary failure
- TC047: Transaction state persistence during recovery
- TC048: Transaction compensation for partial failures
- TC049: Transaction timeout handling and cleanup

#### 4.2 Data Consistency Tests
- TC050: Data consistency check after failed transactions
- TC051: Balance reconciliation after failed transfers
- TC052: Audit trail maintenance during recovery
- TC053: Transaction status synchronization
- TC054: Data integrity verification post-recovery

### 5. Edge Case Tests

#### 5.1 Boundary Value Tests
- TC055: Transfer with minimum allowed amount
- TC056: Transfer with maximum allowed amount
- TC057: Transfer with exact daily limit amount
- TC058: Transfer with exact monthly limit amount
- TC059: Transfer with zero exchange rate

#### 5.2 Performance Tests
- TC060: High volume transfer processing
- TC061: Concurrent transfer handling
- TC062: Response time under load
- TC063: Memory usage during transfers
- TC064: CPU utilization during processing

### 6. Security Tests

#### 6.1 Authentication Tests
- TC065: Successful authentication with valid credentials
- TC066: Failed authentication with invalid credentials
- TC067: Authentication with expired tokens
- TC068: Authentication with revoked access
- TC069: Multi-factor authentication enforcement

#### 6.2 Authorization Tests
- TC070: Authorized user can initiate transfers
- TC071: Unauthorized user cannot initiate transfers
- TC072: Role-based access control validation
- TC073: Permission validation for different user roles
- TC074: Session management security

### 7. Compliance and Regulatory Tests

#### 7.1 Regulatory Tests
- TC075: KYC verification during transfer initiation
- TC076: Anti-Money Laundering (AML) checks
- TC077: Know Your Customer (KYC) compliance
- TC078: Transaction monitoring for suspicious activity
- TC079: Reporting to regulatory authorities

#### 7.2 Data Protection Tests
- TC080: Data encryption during transmission
- TC081: Data encryption at rest
- TC082: Secure storage of sensitive information
- TC083: Privacy compliance for customer data
- TC084: Data retention policy enforcement

## Test Case Details

### TC001: Successful currency conversion from INR to JPY
**Preconditions:** 
- INR and JPY currencies are onboarded
- Exchange rate for INR/JPY is available
- Source account has sufficient funds

**Steps:**
1. Initiate currency conversion from INR to JPY
2. Enter amount in INR
3. Verify exchange rate is applied
4. Confirm conversion details
5. Execute conversion

**Expected Results:**
- Conversion completes successfully
- Correct amount in JPY is calculated
- Appropriate fees are deducted
- Transaction is recorded in audit log

### TC016: Currency conversion with invalid source currency
**Preconditions:**
- Invalid currency code is entered

**Steps:**
1. Attempt currency conversion with invalid source currency
2. Enter amount in invalid currency
3. Submit conversion request

**Expected Results:**
- Error message displayed for invalid currency
- Conversion is not processed
- User is prompted to enter valid currency

### TC035: Transfer fails due to gateway unavailability
**Preconditions:**
- Gateway service is temporarily down

**Steps:**
1. Initiate transfer from source gateway
2. Gateway service returns error
3. System attempts recovery

**Expected Results:**
- Error message displayed to user
- Transaction is logged as failed
- Retry mechanism initiated
- User notified of temporary issue

### TC045: Transaction rollback on system failure
**Preconditions:**
- Transaction is in progress
- System experiences failure

**Steps:**
1. Start transaction processing
2. Simulate system failure during processing
3. Trigger rollback mechanism

**Expected Results:**
- Funds are returned to source account
- Transaction status is marked as rolled back
- Audit trail shows rollback action
- No partial transaction remains in system

## Test Execution Requirements

### Environment Setup
- Test environment with all components deployed
- Currency, country, and gateway configurations
- Sample accounts with sufficient balances
- Test data for various scenarios

### Test Data Requirements
- Valid currency pairs (INR/JPY, USD/EUR, etc.)
- Test accounts for both source and destination
- Exchange rate data for different time periods
- User credentials for different roles
- Transaction limits and thresholds

### Success Criteria
- All positive test cases pass
- All negative test cases return expected errors
- Failure scenarios recover gracefully
- Security measures are enforced
- Compliance requirements are met