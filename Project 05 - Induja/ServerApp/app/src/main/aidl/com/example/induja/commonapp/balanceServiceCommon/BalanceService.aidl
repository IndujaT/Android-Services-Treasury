// BalanceService.aidl
package com.example.induja.commonapp.balanceServiceCommon;

import com.example.induja.commonapp.balanceServiceCommon.DailyCash;

// Declare any non-default types here with import statements

interface BalanceService {
    boolean createDatabase();
    DailyCash[] dailyCash(int yyyy, int mm, int dd, int workingDays);
    int calculate(int num1, int num2);
}
