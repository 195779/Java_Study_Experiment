package PTA.Firstquestion;

public class DateUtil {
    private int year;
    private int month;
    private int day;

    public DateUtil(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean checkInputValidity() {
        boolean judgeOfSafe = true;
        if(year > 2020 || year < 1820 || month < 1 || month > 12 || day < 1 || day > 31) {
            judgeOfSafe = false;
        } else {
            if(isThirtyDaysOfMonth(month)) {
                if(day >= 31) {
                    judgeOfSafe = false;
                }
            }
            if(month == 2) {
                if(isLeapYear(year)) {
                    if(day > 29) {
                        judgeOfSafe = false;
                    }
                } else {
                    if(day > 28) {
                        judgeOfSafe = false;
                    }
                }
            }
        }
        return judgeOfSafe;
    }

    public boolean isLeapYear(int year) {
        if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThirtyDaysOfMonth(int month) {
        if(month == 4 || month == 6 || month == 9 || month == 11) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThirstyPlusOfMonth(int month) {
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return true;
        } else {
            return false;
        }
    }

    public int getDisOfThisMonthLastDay(int year, int month, int day) {
        int disOfThisMonthLastDay = 0;
        if(isThirtyDaysOfMonth(month)) {
            disOfThisMonthLastDay = 30 - day;
        }
        if(isThirstyPlusOfMonth(month)) {
            disOfThisMonthLastDay = 31 - day;
        }
        if(month == 2) {
            if(isLeapYear(year)) {
                disOfThisMonthLastDay = 29 - day;
            } else {
                disOfThisMonthLastDay = 28 - day;
            }
        }
        return disOfThisMonthLastDay;
    }

    public int getDisOfThisMonthFirstDay(int year, int month, int day) {
        return day;
    }

    public int getDistOfThisYearLastDay(int year, int month, int day) {
        int disOfThisYearLastDay = 365;
        if(isLeapYear(year)) {
            disOfThisYearLastDay = 366;
        }
        int xOfDis = getDisOfThisYearFirstDay(year, month, day);
        disOfThisYearLastDay -= xOfDis;
        return disOfThisYearLastDay;
    }

    public int getDisOfThisYearFirstDay(int year, int month, int day) {
        int xOfDis = day;
        for(int i = 1; i < month; i++) {
            if(isThirtyDaysOfMonth(i)) {
                xOfDis += 30;
            }
            if(isThirstyPlusOfMonth(i)) {
                xOfDis += 31;
            }
            if(i == 2) {
                if(isLeapYear(year)) {
                    xOfDis += 29;
                } else {
                    xOfDis += 28;
                }
            }
        }
        return xOfDis;
    }

    public int getDayOfThisMonth(int year, int month) {
        if(month == 2) {
            if(isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        } else {
            if(isThirtyDaysOfMonth(month)) {
                return 30;
            } else if(isThirstyPlusOfMonth(month)) {
                return 31;
            }
        }
        return 0;
    }

    public DateUtil getNextNDays(int n) {
        int disOfThisMonthLastDay = getDisOfThisMonthLastDay(year, month, day);
        int disOfThisYearLastDay = getDistOfThisYearLastDay(year, month, day);
        if(n <= disOfThisMonthLastDay) {
            day += n;
            return new DateUtil(year, month, day);
        } else {
            if(n <= disOfThisYearLastDay) {
                while(n <= disOfThisYearLastDay) {
                    n -= disOfThisMonthLastDay;
                    month++;
                    day = 0;
                    disOfThisMonthLastDay = getDisOfThisMonthLastDay(year, month, day);
                    if(n <= disOfThisMonthLastDay) {
                        day = n;
                        break;
                    }
                }
                return new DateUtil(year, month, day);
            } else {
                while(n > disOfThisYearLastDay) {
                    n -= disOfThisYearLastDay;
                    year++;
                    month = 1;
                    day = 0;
                    disOfThisYearLastDay = getDistOfThisYearLastDay(year, month, day);
                    if(n <= disOfThisYearLastDay) {
                        disOfThisMonthLastDay = getDisOfThisMonthLastDay(year, month, day);
                        while(n <= disOfThisYearLastDay) {
                            n -= disOfThisMonthLastDay;
                            month++;
                            day = 0;
                            disOfThisMonthLastDay = getDisOfThisMonthLastDay(year, month, day);
                            if(n <= disOfThisMonthLastDay) {
                                day = n;
                                break;
                            }
                        }
                        break;
                    }
                }
                return new DateUtil(year, month, day);
            }
        }
    }

    public DateUtil getPreviousNDays(int n) {
        int disOfThisMonthFirstDay = getDisOfThisMonthFirstDay(year, month, day);
        if(n <= disOfThisMonthFirstDay) {
            day -= n;
            return new DateUtil(year, month, day);
        } else if(n > disOfThisMonthFirstDay) {
            int disOfThisYearFirstDay = getDisOfThisYearFirstDay(year, month, day);
            if(n <= disOfThisYearFirstDay) {
                while(n <= disOfThisYearFirstDay) {
                    if(n == disOfThisYearFirstDay) {
                        year--;
                        month = 12 ;
                        day = 31;
                        return new DateUtil(year,month,day);
                    } else {
                        n -= disOfThisMonthFirstDay;
                        month--;
                        day = getDayOfThisMonth(year, month);
                        disOfThisMonthFirstDay = getDisOfThisMonthFirstDay(year, month, day);
                        if(n <= disOfThisMonthFirstDay) {
                            day -= n;
                            break;
                        }
                    }
                }
                return new DateUtil(year, month, day);
            } else if(n > disOfThisYearFirstDay) {
                while(n > disOfThisYearFirstDay) {
                    n -= disOfThisYearFirstDay;
                    year--;
                    month = 12;
                    day = getDayOfThisMonth(year, month);
                    disOfThisYearFirstDay = getDisOfThisYearFirstDay(year, month, day);
                    if(n <= disOfThisYearFirstDay) {
                        while(n <= disOfThisYearFirstDay) {
                            disOfThisMonthFirstDay = getDisOfThisMonthFirstDay(year, month, day);
                            if(n <= disOfThisMonthFirstDay) {
                                day -= n;
                                break;
                            } else {
                                n -= disOfThisMonthFirstDay;
                                month--;
                                day = getDayOfThisMonth(year, month);
                                disOfThisYearFirstDay = getDisOfThisYearFirstDay(year, month, day);
                            }
                        }
                        break;
                    }
                }
                return new DateUtil(year, month, day);
            }
        }
        return new DateUtil(year, month, day);
    }

    public boolean compareDates(DateUtil date) {
        boolean judge = true;
        if(date.getYear() > year) {
            judge = true;
        }
        if(date.getYear() == year) {
            if(date.getMonth() > month) {
                judge = true;
            }
            if(date.getMonth() < month) {
                judge = false;
            }
            if(date.getMonth() == month) {
                if(date.getDay() > day) {
                    judge = true;
                }
                if(date.getDay() < day) {
                    judge = false;
                }
            }
        }
        if(date.getYear() < year) {
            judge = false;
        }
        return judge;
    }

    public boolean equalTwoDates(DateUtil date) {
        if(date.getYear() == year && date.getMonth() == month && date.getDay() == day) {
            return true;
        } else {
            return false;
        }
    }

    public int getDaysofDates(DateUtil date) {
        if(equalTwoDates(date)) {
            return 0;
        } else {
            if(year == date.getYear()) {
                int dis = getDisOfThisYearFirstDay(date.getYear(), date.getMonth(), date.getDay())
                        - getDisOfThisYearFirstDay(year, month, day);
                if(compareDates(date)) {
                    return dis;
                } else {
                    return -dis;
                }
            } else {
                if(compareDates(date)) {
                    int dis = getDistOfThisYearLastDay(year, month, day);
                    while(this.year < date.getYear()) {
                        year++;
                        month = 1;
                        day = 0;
                        if(year == date.getYear()) {
                            dis += getDisOfThisYearFirstDay(date.getYear(), date.getMonth(), date.getDay());
                            break;
                        } else {
                            dis += getDistOfThisYearLastDay(year, month, day);
                        }
                    }
                    return dis;
                } else {
                    int dis = getDisOfThisYearFirstDay(year, month, day);
                    while(year > date.getYear()) {
                        year--;
                        month = 12;
                        day = 31;
                        if(year == date.getYear()) {
                            dis += getDistOfThisYearLastDay(date.getYear(), date.getMonth(), date.getDay());
                            break;
                        } else {
                            dis += getDistOfThisYearLastDay(year, month, day);
                        }
                    }
                    return dis;
                }
            }
        }
    }

    public String showDate() {
        return year + "-" + month + "-" + day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
