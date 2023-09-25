import java.util.regex.Pattern;

public class Phone {
    private static final String BRAND_PATTERN = "[A-Za-z\\s]+";
    private static final String YEAR_OF_ISSUE_PATTERN = "\\d{4}";
    private static final String MEMORY_PATTERN = "\\d{3}";
    private String brand;
    private String model;
    private String yearOfIssue;
    private String memory;

    public void validate() throws PhoneException {
        if (!Pattern.matches(BRAND_PATTERN, brand)) {
            throw new PhoneException("Invalid brand name");
        }
        if (!Pattern.matches(YEAR_OF_ISSUE_PATTERN,yearOfIssue)) {
            throw new PhoneException("Invalid years if issue");
        }
        if (Integer.parseInt(memory)<0) {
            throw new PhoneException("Invalid memory");
        }
    }

    public Phone() {
    }

    public Phone(String brand, String model, String yearOfIssue, String memory) {
        this.brand = brand;
        this.model = model;
        this.yearOfIssue = yearOfIssue;
        this.memory = memory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                ", memory=" + memory +
                '}';
    }
}
