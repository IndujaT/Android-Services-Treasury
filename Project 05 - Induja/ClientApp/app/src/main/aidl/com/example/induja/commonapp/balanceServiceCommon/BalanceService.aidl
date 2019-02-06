// BalanceService.aidl
package com.example.induja.commonapp.balanceServiceCommon;

// Declare any non-default types here with import statements

import com.example.induja.commonapp.balanceServiceCommon.DailyCash;

interface BalanceService {
    boolean createDatabase();
    DailyCash[] dailyCash(int yyyy, int mm, int dd, int workingDays);
    int calculate(int num1, int num2);
}
