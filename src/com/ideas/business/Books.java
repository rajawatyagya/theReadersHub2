package com.ideas.business;

public enum Books {
    SARAH_SZE("Sarah Sze","English",1),
    BOTTICELLI("Botticelli","English",1),
    RAW("Raw", "English",1),
    THE_WATCHMEN("The Watchmen", "English", 1),
    MAHABHARATA("Mahabharata", "Hindi", 1),
    JEU_DU_SPORT("Jeu du sport", "French", 1),
    THE_DARK_NIGHT("The Dark Night", "English", 1),
    THE_DARK_NIGHT_RISES("The Dark Night Rises", "English", 2),
    INTO_THIN_AIR("Into Thin Air", "English", 1),
    EXPLORE_LE_MONDE("Explorer le monde", "French", 1),
    THE_LEAN_STARTUP("The Lean Startup", "English", 1),
    THE_ART_OF_THE_SMART("The Art Of The Smart", "English", 1),
    HARRY_POTTER_1("Harry Potter", "English", 1),
    HARRY_POTTER_2("Harry Potter", "English", 2),
    HARRY_POTTER_3("Harry Potter", "English", 3),
    HARRY_POTTER_4("Harry Potter", "English", 4),
    GUNS_AND_GEMS_AND_STEEL("Guns,Gems, and Steel", "English", 1),
    ODYSSEY("Odyssey","English",1);
    private String name;
    private String language;
    private int version;
    Books(String name,String language,int version) {
        this.setName(name);
        this.setLanguage(language);
        this.setVersion(version);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLanguage() {
        return   language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
}
