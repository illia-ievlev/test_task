package com.ievlev.test_task3.model;

import com.ievlev.test_task3.converter.DegreeConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "lectors")
public class Lector {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Convert(converter = DegreeConverter.class)
//    @Enumerated(EnumType.STRING)
    @Column(name = "degree")
    @NotNull
    private Degree degree;

    @Column(name = "salary")
    @Min(0)
    private BigDecimal salary; //it is better to use  BigDecimal when dealing with money in Java

    @Override
    public String toString() {
        return name;
    }

    public Lector() {
    }

    public Lector(Long id, String name, Degree degree, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.salary = salary;
    }

    public Lector(String name, Degree degree, BigDecimal salary) {
        this.name = name;
        this.degree = degree;
        this.salary = salary;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Lector lector = (Lector) o;
        return getId() != null && Objects.equals(getId(), lector.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
