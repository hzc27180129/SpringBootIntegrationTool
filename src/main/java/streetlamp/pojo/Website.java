package streetlamp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Website {
    private Integer id;
    private String name;
    private String url;
    private int age;
    private String country;

}
