package tut.by.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Sections {
    MailBox("Почта"),
    Job("Работа"),
    Finance("Финансы"),
    Poster("Афиша");

    private String section;


}
