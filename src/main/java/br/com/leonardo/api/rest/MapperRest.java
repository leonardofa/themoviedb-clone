package br.com.leonardo.api.rest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.leonardo.api.representation.BaseDTO;
import br.com.leonardo.entity.BaseEntity;

public class MapperRest<M extends BaseEntity, R extends BaseDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @SuppressWarnings("unchecked")
  private Class<M> getModelClass() {
    Type type = getClass().getGenericSuperclass();
    return (Class<M>) ((ParameterizedType) type).getActualTypeArguments()[0];
  }

  @SuppressWarnings("unchecked")
  private Class<R> getRepresentationClass() {
    Type type = getClass().getGenericSuperclass();
    return (Class<R>) ((ParameterizedType) type).getActualTypeArguments()[1];
  }

  protected M toModel(R dto) {
    return modelMapper.map(dto, getModelClass());
  }

  protected List<M> toModel(List<R> dtoList) {
    return dtoList.stream().map(dto -> toModel(dto)).collect(Collectors.toList());
  }

  protected R fromModel(M model) {
    return modelMapper.map(model, getRepresentationClass());
  }

  protected List<R> fromModel(List<M> modelList) {
    return modelList.stream().map(model -> fromModel(model)).collect(Collectors.toList());
  }

}
