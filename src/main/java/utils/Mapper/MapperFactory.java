package utils.Mapper;

public abstract class MapperFactory {

    public static ISqlMapper getMapper(String model) {
        return switch (model) {
            case ("User") -> new UserMapper();
            case ("Product") -> new ProductMapper();
            case ("Bucket") -> new BucketMapper();
            default -> null;
        };
    }

}
