/*
 * %W% %E% Alex Martysiuk
 *
 * Копирайт (c) 1993-1996 Sun Microsystems, Inc. Все права защищены.
 *
 * Это программное обеспечение является конфиденциальной и закрытой информацией Sun
 * Microsystems, Inc. («Конфиденциальная информация»). Вы не должены
 * раскрывать такую Конфиденциальную информацию и использовать ее только в
 * соответствии с условиями лицензионного соглашения, в которых вы состоите
 * с Sun.
 *
 * SUN НЕ ДАЁТ НИКАКИХ ГАРАНТИЙ, ЯВНЫХ ИЛИ КОСВЕННЫХ (ВКЛЮЧАЯ - НО НЕ
 * ОГРАНИЧИВАЯСЬ ИМИ - ГАРАНТИИ РЕАЛИЗУЕМОСТИ), СООТВЕТСТВИЯ ОПРЕДЕЛЁННОМУ
 * НАЗНАЧЕНИЮ ИЛИ НЕНАРУШЕНИЯ УСЛОВИЙ, ЧТО СОДЕРЖИМОЕ ДАННОЙ СПЕЦИФИКАЦИИ
 * ПОДХОДИТ ДЛЯ КАКИХ-ЛИБО ЦЕЛЕЙ ИЛИ ЧТО ЛЮБОЕ ИСПОЛЬЗОВАНИЕ ИЛИ РЕАЛИЗАЦИЯ
 * ТАКОГО СОДЕРЖИМОГО НЕ БУДЕТ НАРУШАТЬ КАКИХ-ЛИБО ПАТЕНТОВ ТРЕТЬЕЙ СТОРОНЫ,
 * АВТОРСКИХ ПРАВ, КОММЕРЧЕСКОЙ ТАЙНЫ ИЛИ ИНЫХ ПРАВ.
 */
package by.martysiuk.springBootApp.models;

import javax.persistence.*;
import java.util.List;

/**
 * Описание класса здесь.
 *
 * @version   29 10 2021
 * @author    Alex Martysiuk
 */
@Entity
@Table(name = "routs")
public class Rout {
    /* Здесь может идти комментарий реализации класса. */

    /** Комментарий, документирующий id*/
    private int id;

    /**
     * Документирующий комментарий к name, который бывает
     * более чем одна строка
     */
    private String name;

    /**
     * Документирующий комментарий к price, который бывает
     * более чем одна строка
     */
    private double price;

    /** Комментарий, документирующий ticketList*/
    @OneToMany(mappedBy = "rout", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;

    /**
     * ...комментарий, документирующий метод doSomething...
     */
    public Rout() {}

    /**
     * ...комментарий, документирующий метод doSomething...
     */
    public Rout(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * ...комментарий, документирующий метод getPrice...
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * ...комментарий, документирующий метод doSomething...
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * ...комментарий, документирующий метод doSomethingElse...
     * @param name описание
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ...комментарий, документирующий метод doSomething...
     */
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    /**
     * ...комментарий, документирующий метод doSomething...
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
