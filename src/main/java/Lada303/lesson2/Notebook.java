package Lada303.lesson2;

import java.util.Objects;

public class Notebook {

    private static int counter = 0;

    int id;
    int price;
    int ram;
    Manufacturer manufacturer;

    public Notebook(int price, int ram, Manufacturer manufacturer) {
        this.id = counter++;
        this.price = price;
        this.ram = ram;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getRam() {
        return ram;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notebook that)) return false;
        return this.price == that.price && this.ram == that.ram && this.manufacturer == that.manufacturer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getRam());
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", price=" + price +
                ", ram=" + ram +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
