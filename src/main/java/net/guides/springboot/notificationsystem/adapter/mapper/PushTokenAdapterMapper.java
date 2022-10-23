package net.guides.springboot.notificationsystem.adapter.mapper;

import com.vasl.ario.crudutil.api.adapter.mapper.CRUDAdapterMapper;
import net.guides.springboot.notificationsystem.dto.CreatePushTokenDto;
import net.guides.springboot.notificationsystem.dto.GetPushTokenDto;
import net.guides.springboot.notificationsystem.model.PushToken;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PushTokenAdapterMapper extends CRUDAdapterMapper<PushToken, CreatePushTokenDto> {

    PushTokenAdapterMapper INSTANCE = Mappers.getMapper(PushTokenAdapterMapper.class);
    GetPushTokenDto pushTokenToGetPushTokenDto(PushToken pushToken);

}

