package com.ideas.business;

import java.time.LocalDate;
import java.util.Map;
import java.util.EnumMap;


public class Member {
    private Membership pack;
    private EnumMap<Books,LocalDate> heldBooks;
    private int extraBookCount;
    private Books extraBook;
    private LocalDate membershipIssueDate;
    public Member() {
        setExtraBookCount(0);
        setHeldBooks();
    }
    public Member(Membership packName) {
        setPack(packName);
        setExtraBookCount(0);
        setHeldBooks();
    }
    public Member(Membership packName, LocalDate membershipIssueDate) {
        setPack(packName);
        setMembershipIssueDate(membershipIssueDate);
        setExtraBookCount(0);
        setHeldBooks();
    }
    private void setMembershipIssueDate(LocalDate membershipIssueDate) {
        this.membershipIssueDate =membershipIssueDate;
    }
    public LocalDate getMembershipIssueDate(){
        return membershipIssueDate;
    }
    private void setHeldBooks() {
        this.heldBooks = new EnumMap<>(Books.class);
    }
    public Membership getPack() {
        return pack;
    }
    public void setPack(Membership pack) {
        this.pack = pack;
    }
    public Map<Books,LocalDate> getHeldBooks() {
        return heldBooks;
    }
    public int getExtraBookCount() {
        return extraBookCount;
    }
    public void setExtraBookCount(int extraBookCount) {
        this.extraBookCount =extraBookCount;
    }

    public Books getExtraBook() {
        return extraBook;
    }

    public void setExtraBook(Books extraBook) {
        this.extraBook = extraBook;
    }
    public void addBook(Books bookName,LocalDate issueDate){
        this.heldBooks.put(bookName, issueDate);
    }

    public void removeBook(Books bookName) {
        if(bookName.equals(extraBook))
            extraBook =null;
        this.heldBooks.remove(bookName);
    }
}
