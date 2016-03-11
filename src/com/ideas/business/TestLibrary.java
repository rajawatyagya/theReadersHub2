package com.ideas.business;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TestLibrary {
    Map<Books,String> category = new EnumMap<>(Books.class);
    Member member1 =new Member(Membership.IVORY,LocalDate.now());
    Member member3 =new Member(Membership.GOLD,LocalDate.now());
    Member member2 =new Member(Membership.SILVER,LocalDate.now());
    Member member4 =new Member(Membership.IVORY,LocalDate.now().minusYears(1));
    Member member5 =new Member(Membership.IVORY,LocalDate.now());
    List<Member> members=new LinkedList<>();
    private static final String ART = "Art";
    private static final String COOKING = "Cooking";
    private static final String FICTION = "Fiction";
    private static final String SPORTS = "Sports";
    private static final String BUSINESS = "Business";
    private static final String HISTORY = "History";
    private static final String POETRY = "Poetry";
    static List<Object> people = new LinkedList<>();
    @Before
    public void initialize() {
        category.put(Books.SARAH_SZE,ART);
        category.put(Books.BOTTICELLI,ART);
        category.put(Books.RAW,COOKING);
        category.put(Books.THE_WATCHMEN,FICTION);
        category.put(Books.MAHABHARATA,FICTION);
        category.put(Books.JEU_DU_SPORT,FICTION);
        category.put(Books.THE_DARK_NIGHT,FICTION);
        category.put(Books.THE_DARK_NIGHT_RISES,FICTION);
        category.put(Books.INTO_THIN_AIR,SPORTS);
        category.put(Books.EXPLORE_LE_MONDE,ART);
        category.put(Books.THE_LEAN_STARTUP,BUSINESS);
        category.put(Books.THE_ART_OF_THE_SMART,BUSINESS);
        category.put(Books.HARRY_POTTER_1,FICTION);
        category.put(Books.HARRY_POTTER_2,FICTION);
        category.put(Books.HARRY_POTTER_3,FICTION);
        category.put(Books.HARRY_POTTER_4,FICTION);
        category.put(Books.GUNS_AND_GEMS_AND_STEEL,HISTORY);

        LocalDate today= LocalDate.now();

        member1.addBook(Books.HARRY_POTTER_1, today);
        member1.addBook(Books.HARRY_POTTER_2, today);
        member2.addBook(Books.THE_WATCHMEN, today);
        member2.addBook(Books.THE_DARK_NIGHT, today);
        member2.addBook(Books.HARRY_POTTER_1, today);
        member3.addBook(Books.THE_WATCHMEN, today);
        member3.addBook(Books.THE_DARK_NIGHT, today);
        member3.addBook(Books.HARRY_POTTER_1, today);
        member3.addBook(Books.GUNS_AND_GEMS_AND_STEEL, today);
        members.add(member1);
        members.add(member2);
        members.add(member3);
        people.add(member1);
        people.add(member2);
        people.add(member3);
        people.add(member4);
        people.add("member_5");
        people.add("member_6");
        people.add("member_7");
        people.add("member_8");
        people.add("member_9");
        people.add("member_10");
        people.add("member_11");
        people.add("member_12");
        people.add("member_13");
        people.add("member_14");
        people.add("member_15");
        people.add("member_16");
        people.add("member_17");
        people.add("member_18");
        people.add("member_19");
        people.add("member_20");
        people.add("member_21");
        people.add("member_22");
        people.add("member_23");
        people.add("member_24");
        people.add("member_25");
        people.add("member_26");
        people.add("member_27");
        people.add("member_28");
        people.add("member_29");
        people.add("member_30");
    }
    @Test
    public void allCategoriesOfTheBooks() {
        Library library = new Library(category,members);
        assertTrue(library.getCategories().containsValue(ART));
        assertTrue(library.getCategories().containsValue(BUSINESS));
        assertTrue(library.getCategories().containsValue(SPORTS));
        assertTrue(library.getCategories().containsValue(COOKING));
        assertTrue(library.getCategories().containsValue(HISTORY));
    }
    @Test
    public void allLanguagesOfBooks(){
        Library library = new Library(category,members);
        List<String> languages = new LinkedList<>();
        languages.add("English");
        languages.add("Hindi");
        languages.add("French");
        languages.add("Spanish");
        library.getCategories().forEach((k,v) -> assertTrue(languages.contains(k.getLanguage())) );
    }
    @Test
    public void membershipDetailsOfIvory(){
        assertEquals(3,Membership.IVORY.getBooksAllowedAtATime());
        assertEquals(7,Membership.IVORY.getPeriodAllowed());
        assertEquals(20,Membership.IVORY.getChargesForExtraDay());
        assertEquals(1000,Membership.IVORY.getPackageCost());
    }
    @Test
    public void membershipDetailsOfSilver(){
        assertEquals(4,Membership.SILVER.getBooksAllowedAtATime());
        assertEquals(10,Membership.SILVER.getPeriodAllowed());
        assertEquals(30,Membership.SILVER.getChargesForExtraDay());
        assertEquals(2000,Membership.SILVER.getPackageCost());
    }
    @Test
    public void membershipDetailsOfGold(){
        assertEquals(5,Membership.GOLD.getBooksAllowedAtATime());
        assertEquals(15,Membership.GOLD.getPeriodAllowed());
        assertEquals(40,Membership.GOLD.getChargesForExtraDay());
        assertEquals(3000,Membership.GOLD.getPackageCost());
    }
    @Test
    public void membershipDetailsOfPlatinum(){
        assertEquals(6,Membership.PLATINUM.getBooksAllowedAtATime());
        assertEquals(20,Membership.PLATINUM.getPeriodAllowed());
        assertEquals(50,Membership.PLATINUM.getChargesForExtraDay());
        assertEquals(5000,Membership.PLATINUM.getPackageCost());
    }
    @Test
    public void memberDetails(){
       assertEquals(Membership.IVORY.name(),member1.getPack().name());
    }
    @Test
    public void shouldNotIssueIfMoreThanTwoBooksOfSameSequelAreHeld() throws LibraryException {
        Library library = new Library(category,members);
        assertFalse(library.requestBook(Books.HARRY_POTTER_3, member1));
    }
    @Test
    public void shouldNotIssueIfMoreThanThreeBooksOfSameCategoryAndLanguageAreHeld() throws LibraryException {
        Library library = new Library(category,members);
        assertFalse(library.requestBook(Books.HARRY_POTTER_2, member2));
    }
    @Test
    public void shouldNotIssueIfMoreThanFourBooksOfSameLanguageAreHeld() throws LibraryException {
        Library library = new Library(category,members);
        assertFalse(library.requestBook(Books.HARRY_POTTER_2, member3));
    }
    @Test
    public void printReceiptIfBookHeldForMoreDaysThanAllowed() throws LibraryException {
        Library library = new Library(category,members);
        LocalDate returnDate = LocalDate.now().plusDays(8);
        assertEquals(20,library.printReceipt(member1,Books.HARRY_POTTER_1,returnDate));
    }
    @Test
    public void printNoDuesIfBookIsHeldForExactlyAsDaysAllowed() throws LibraryException {
        Library library = new Library(category,members);
        LocalDate returnDate = LocalDate.parse("2016-03-09");
        assertEquals("No Dues",library.printReceipt(member1,Books.HARRY_POTTER_1,returnDate));
    }
    @Test
    public void shouldIssueOneExtraBookIfMemberRequestsBooksOutOfAllowedLimit() throws LibraryException {
        member4.addBook(Books.THE_WATCHMEN, LocalDate.now());
        member4.addBook(Books.HARRY_POTTER_1, LocalDate.now());
        member4.addBook(Books.HARRY_POTTER_2, LocalDate.now());
        members.add(member4);
        Library library = new Library(category, members);
        assertTrue(library.requestBook(Books.BOTTICELLI, member4));
    }
    @Test
    public void printReceiptIfMemberReturnsTheExtraBook() throws LibraryException {
        member5.addBook(Books.THE_WATCHMEN, LocalDate.now());
        member5.addBook(Books.HARRY_POTTER_1, LocalDate.now());
        member5.addBook(Books.HARRY_POTTER_2, LocalDate.now());
        member5.addBook(Books.BOTTICELLI, LocalDate.now());
        member5.setExtraBook(Books.BOTTICELLI);
        member5.setExtraBookCount(1);
        members.add(member5);
        LocalDate returnDate = LocalDate.now().plusDays(8);
        Library library = new Library(category, members);
        assertEquals(70,library.printReceipt(member5, Books.BOTTICELLI,returnDate));
    }
    @Test
    public void checkIfBookAvailableAndThenGetACopy() throws LibraryException {
        Library library = new Library(category, members);
        assertTrue(library.requestBook(Books.BOTTICELLI, member1));
    }
    @Test(expected = LibraryException.class)
    public void ifPackageExpiresNoBookShouldBeAllowedToBorrow() throws LibraryException {
        Library library = new Library(category, members);
        assertTrue(library.requestBook(Books.BOTTICELLI, member4));
    }
    @Test
    public void printReceiptIfPackageExpiresAndBooksReturnedWithinDeadline(){
        Library library = new Library(category, members);
        assertEquals(1000,library.refundIssued(member4));
    }
    @Test
    public void deductDepositIfPackageExpiresAndBooksNotReturnedWithinDeadline(){
        member4.addBook(Books.THE_WATCHMEN, LocalDate.now());
        member4.addBook(Books.HARRY_POTTER_1, LocalDate.now());
        member4.addBook(Books.HARRY_POTTER_2, LocalDate.now());
        member4.addBook(Books.BOTTICELLI, LocalDate.now());
        member4.setExtraBook(Books.BOTTICELLI);
        member4.setExtraBookCount(1);
        members.add(member4);
        Library library = new Library(category, members,LocalDate.parse("2016-05-11"));
        assertEquals(0,library.refundIssued(member4));
    }
    @Test
    public void checkIfLibraryIsFull(){
        Library library = new Library(people);
        assertFalse(library.checkForSpace("member_random"));
    }
    @Test
    public void checkIfAPersonComesOutTheSpaceIsAllocatedToTheNextInQueue() throws LibraryException {
        Library library = new Library(people);
        library.checkForSpace("member_31");
        library.exitLibrary("member_25");
        assertFalse(library.checkForSpace("member_32"));
    }
    @Test
    public void shouldBeAbleToAddCategories(){
        category.put(Books.ODYSSEY,POETRY);
        Library library = new Library(category,members);
        assertTrue(library.getCategories().containsValue(ART));
        assertTrue(library.getCategories().containsValue(BUSINESS));
        assertTrue(library.getCategories().containsValue(SPORTS));
        assertTrue(library.getCategories().containsValue(COOKING));
        assertTrue(library.getCategories().containsValue(HISTORY));
        assertTrue(library.getCategories().containsValue(POETRY));
    }
}