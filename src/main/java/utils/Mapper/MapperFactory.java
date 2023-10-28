package utils.Mapper;

public abstract class MapperFactory {

    public static ISqlMapper getMapper(String model) {
        switch (model) {
            case  ("User"):
                return new UserMapper();

        }
        return null;
    }

}
