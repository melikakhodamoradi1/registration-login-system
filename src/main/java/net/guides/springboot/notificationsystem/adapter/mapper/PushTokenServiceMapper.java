package net.guides.springboot.notificationsystem.adapter.mapper;


import com.vasl.ario.crudutil.service.mapper.CRUDServiceMapper;
import net.guides.springboot.notificationsystem.model.PushToken;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PushTokenServiceMapper extends CRUDServiceMapper<PushToken> {
    PushTokenServiceMapper INSTANCE = Mappers.getMapper(PushTokenServiceMapper.class);
}
