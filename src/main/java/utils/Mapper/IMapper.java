package utils.Mapper;

public interface IMapper<Source, Destination> {
    public Destination map(Source entity);
}
