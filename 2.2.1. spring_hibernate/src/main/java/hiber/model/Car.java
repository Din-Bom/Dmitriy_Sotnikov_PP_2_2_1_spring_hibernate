package hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private final String model;

    @Column(name = "series")
    private final Integer series;

    @OneToOne(mappedBy = "car")
    private User user;


    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    protected Car() {
        this.model = null;
        this.series = null;
    }

    public String getModel() {
        return model;
    }


    public int getSeries() {
        return series;
    }


    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
