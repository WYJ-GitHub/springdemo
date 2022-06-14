package apple;

/**
 * @program: springdemo
 * @description
 * @author: 吴云杰
 * @create: 2022-06-14 15:24
 **/
public class Apple {
    private String color;
    private String name;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight;

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
