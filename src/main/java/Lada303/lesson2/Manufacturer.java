package Lada303.lesson2;

public enum Manufacturer {

    LENOVO(0),
    ASOS(1),
    MACNOTE(2),
    ESER(3),
    XAMIOU(4);

    public static final int NUMBERS = 5;

    private final int index;

    Manufacturer(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Manufacturer getManufacturerByIndex(int index) {
        Manufacturer[] manufacturers = Manufacturer.values();
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.index == index) {
                return manufacturer;
            }
        }
        return null;
    }

}
