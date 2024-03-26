package com.ievlev.test_task3.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "departments")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Department.lectorList",
                attributeNodes = {
                        @NamedAttributeNode("lectorList")
                }
        ),
        @NamedEntityGraph(name = "Department.headOfDepartment",
                attributeNodes = {
                        @NamedAttributeNode(value = "headOfDepartment")
                })
})
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    @NotBlank
    private String name;

    @OneToOne
    private Lector headOfDepartment;

    @ManyToMany
    @JoinTable(name = "department_lector",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "lector_id"))
    private List<Lector> lectorList;

    public Department() {
    }

    public Department(Long id, String name, Lector headOfDepartment, List<Lector> lectorList) {
        this.id = id;
        this.name = name;
        this.headOfDepartment = headOfDepartment;
        this.lectorList = lectorList;
    }

    public Department(String name, Lector headOfDepartment, List<Lector> lectorList) {
        this.name = name;
        this.headOfDepartment = headOfDepartment;
        this.lectorList = lectorList;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Department department = (Department) o;
        return getId() != null && Objects.equals(getId(), department.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
