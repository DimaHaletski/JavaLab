package by.gsu.pms;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsRecordDTO {
    private int product_id;
    private String material_name;
    private int material_cost;
    private String worker_name;
    private int worker_salary;
    private int making_time;

}


