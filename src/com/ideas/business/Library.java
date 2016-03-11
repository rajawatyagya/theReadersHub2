package com.ideas.business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Library {
    private Map<Books, String> categories;
    private List<Member> memberList;
    private int versionCount = 0;
    private int languageCount = 0;
    private int categoryCount = 0;
    private boolean flag = false;
    private int depositMoney = 1000;
    private Map<Books, String> issuedBooks;
    private LocalDate expiryDate;
    private LocalDate today;
    private List<Object> peopleSitting;
    private Queue<Object> waitingList;

    public Library(Map<Books, String> categories) {
        this.setCategories(categories);
        this.setMemberList(new LinkedList<>());
        this.today = LocalDate.now();
        this.setPeopleSitting(new LinkedList<>());
        waitingList = new LinkedList<>();
    }

    public Library(Map<Books, String> category, List<Member> members) {
        this.setCategories(category);
        this.setMemberList(members);
        this.today = LocalDate.now();
        this.setPeopleSitting(new LinkedList<>());
        waitingList = new LinkedList<>();
        setIssuedBooks(members);
    }

    public Library(Map<Books, String> category, List<Member> members, LocalDate returnDateAfterExpiry) {
        this.setCategories(category);
        this.setMemberList(members);
        this.today = returnDateAfterExpiry;
        this.setPeopleSitting(new LinkedList<>());
        waitingList = new LinkedList<>();
        setIssuedBooks(members);
    }

    public Library(List<Object> peopleSitting) {
        this.setCategories(new EnumMap<>(Books.class));
        this.setMemberList(new LinkedList<>());
        this.today = LocalDate.now();
        this.setPeopleSitting(peopleSitting);
        waitingList = new LinkedList<>();
    }

    private void setIssuedBooks(List<Member> members) {
        this.issuedBooks = new EnumMap<>(Books.class);
        members.forEach(k ->
            k.getHeldBooks().keySet().forEach(j -> {
                String tempCategory = this.categories.get(j);
                this.issuedBooks.put(j,tempCategory);
            })
        );
    }

    private void setPeopleSitting(List<Object> peopleSitting) {
        this.peopleSitting = peopleSitting;
    }

    private void setDepositMoney(int depositMoney) {
        this.depositMoney = depositMoney;
    }

    private void setMemberList(List<Member> members) {
        this.memberList = members;
    }

    public Map<Books, String> getCategories() {
        return categories;
    }

    private void setCategories(Map<Books, String> categories) {
        this.categories = categories;
    }

    private void incrementCategoryCount() {
        this.setCategoryCount(getCategoryCount() + 1);
    }

    private void incrementLanguageCount() {
        this.setLanguageCount(this.getLanguageCount() + 1);

    }

    private void incrementVersionCount() {
        this.setVersionCount(this.getVersionCount() + 1);

    }

    private int getVersionCount() {
        return versionCount;
    }

    private void setVersionCount(int count) {
        this.versionCount = count;
    }

    private int getLanguageCount() {
        return languageCount;
    }

    private void setLanguageCount(int languageCount) {
        this.languageCount = languageCount;
    }

    private int getCategoryCount() {
        return categoryCount;
    }

    private void setCategoryCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }

    private boolean issueBook(Member memberName, Books bookName) throws LibraryException {
        setCategoryCount(0);
        setLanguageCount(0);
        setVersionCount(0);
        expiryDate = memberName.getMembershipIssueDate().plusYears(1);
        if ((int) ChronoUnit.YEARS.between(memberName.getMembershipIssueDate(), expiryDate) > 0 && memberList.contains(memberName))
        {
            if (memberName.getHeldBooks().size() < memberName.getPack().getBooksAllowedAtATime())
            {
                if (ifAllowed(memberName, bookName))
                    {
                    return updateAllDatabases(memberName, bookName);
                    }
                }
            else if (memberName.getHeldBooks().size() == memberName.getPack().getBooksAllowedAtATime())
                {
                    boolean check = ifAllowed(memberName, bookName);
                    if (memberName.getExtraBookCount() < 1 && check)
                    {
                        memberName.setExtraBookCount(1);
                        memberName.setExtraBook(bookName);
                        return updateAllDatabases(memberName, bookName);
                    }
                }
            }
        else if(!memberList.contains(memberName))
            throw new LibraryException("Not A member");
        else if((int) ChronoUnit.YEARS.between(memberName.getMembershipIssueDate(), expiryDate) <= 0)
            throw new LibraryException("Package Expired");
        return false;
    }

    private boolean updateAllDatabases(Member memberName, Books bookName) {
        memberName.addBook(bookName, LocalDate.now());
        memberList.set(memberList.indexOf(memberName), memberName);
        issuedBooks.put(bookName, categories.get(bookName));
        categories.remove(bookName);
        return true;
    }

    private boolean ifAllowed(Member memberName, Books bookName) {
        memberName.getHeldBooks().forEach((k, v) -> {
            if (bookName.getName().equals(k.getName()) && bookName.getVersion() != k.getVersion()) {
                incrementVersionCount();
            }
            if (bookName.getLanguage().equals(k.getLanguage()))
                incrementLanguageCount();
        });
        if (getLanguageCount() < 4) {
            String cat = getCategories().get(bookName);
            memberName.getHeldBooks().keySet().forEach(k ->
            {
                if (cat.equals(getCategories().get(k)))
                    incrementCategoryCount();
            });
            if (getCategoryCount() < 3 && getVersionCount() < 2) {
                return true;
            }
        }
        return false;
    }

    private int returnBook(Member memberName, Books bookName, LocalDate returnDate) throws LibraryException {
        if (memberList.contains(memberName))
        {
            expiryDate = memberName.getMembershipIssueDate().plusYears(1);
            int extraDays = (int) (ChronoUnit.DAYS.between(expiryDate, returnDate));
            if (extraDays <= 7)
            {
                Member testMemberName = memberList.get(memberList.indexOf(memberName));
                int billForExtraDays = extraDayBillCalculation(testMemberName, bookName, returnDate);
                int billForExtraBook = extraBookBillCalculation(testMemberName, bookName);
                removeBook(testMemberName, bookName);
                categories.put(bookName, issuedBooks.get(bookName));
                issuedBooks.remove(bookName);
                return billForExtraBook + billForExtraDays;
            }
            else
                throw new LibraryException("Exceeded deadline to return");
        }
        else
            throw new LibraryException("Not A member");
    }

    public Object printReceipt(Member memberName, Books bookName, LocalDate returnDate) throws LibraryException {
        int dues = returnBook(memberName, bookName, returnDate);
        if (dues > 0)
            return dues;
        else
            return "No Dues";
    }

    private int extraDayBillCalculation(Member memberName, Books bookName, LocalDate returnDate) {
        long extraDays = (ChronoUnit.DAYS.between(memberName.getHeldBooks().get(bookName), returnDate)) - (memberName.getPack().getPeriodAllowed());
        if (extraDays > 0)
        {
            return (int) (extraDays * memberName.getPack().getChargesForExtraDay());
        }
        return 0;
    }

    private int extraBookBillCalculation(Member memberName, Books bookName) {
        if(memberName.getExtraBook()!=null && memberName.getExtraBook().equals(bookName))
            return 50;
        return 0;
    }

    private void removeBook(Member memberName, Books bookName) {
        memberName.removeBook(bookName);
        memberList.set(memberList.indexOf(memberName), memberName);
    }

    private boolean searchBook(Books bookName) {
        getCategories().forEach((k, v) -> {
            if (k.getName().equals(bookName.getName()))
                changeFlag(true);
        });
        return this.flag;
    }

    private void changeFlag(boolean b) {
        this.flag = b;
    }

    public boolean requestBook(Books bookName, Member memberName) throws LibraryException {
        if (searchBook(bookName) && issueBook(memberName, bookName))
            return true;
        return false;
    }

    public int refundIssued(Member memberName) {
        Map<Books,LocalDate> tempBookList= new EnumMap<>(Books.class);
        tempBookList.putAll(memberName.getHeldBooks());
        if (tempBookList.isEmpty())
            return depositMoney;
        else {
            tempBookList.forEach((k, v) -> {
                        try {
                            setDepositMoney(this.depositMoney - returnBook(memberName, k, this.today));
                        } catch (LibraryException e) {
                            setDepositMoney(0);
                        }
                    }
            );
            return depositMoney;
        }
    }

    public boolean checkForSpace(Object personName) {
        if(this.peopleSitting.size()<30)
        {
            this.peopleSitting.add(personName);
            return true;
        }
        else if(this.peopleSitting.size()==30)
        {
            this.waitingList.add(personName);
            return false;
        }
        return false;
    }

    public void exitLibrary(Object personName) throws LibraryException {
        if(this.peopleSitting.contains(personName))
        {
            this.peopleSitting.remove(personName);
            if(!this.waitingList.isEmpty())
            {
                this.peopleSitting.add(this.waitingList.poll());
            }
        }
        else
            throw new LibraryException("Not Present In The Library");
    }
}
