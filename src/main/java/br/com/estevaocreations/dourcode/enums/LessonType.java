package br.com.estevaocreations.dourcode.enums;

public enum LessonType {
    YT_VIDEO("YT_VIDEO"),
    TEXT("TEXT"),
    EXTERNAL_LINK("EXTERNAL_LINK");
    final String name;
    LessonType(String name) {
        this.name = name;
    }
}
