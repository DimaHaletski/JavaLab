package by.gsu.pms;

public class Mapper {
    public ProductsRecordDTO mapProductsRecordDTO(ProductsRecord productsRecord, Materials materials, Workers workers){
        return ProductsRecordDTO.builder()
                .product_id(productsRecord.getProduct_id())
                .material_name(materials.getMaterial_name())
                .material_cost(materials.getMaterial_cost())
                .worker_name(workers.getWorker_name())
                .worker_salary(workers.getWorker_salary())
                .making_time(productsRecord.getMaking_time())
                .build();
    }
}
