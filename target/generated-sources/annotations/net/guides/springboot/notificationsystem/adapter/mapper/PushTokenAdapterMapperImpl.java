package net.guides.springboot.notificationsystem.adapter.mapper;

import javax.annotation.processing.Generated;
import net.guides.springboot.notificationsystem.dto.CreatePushTokenDto;
import net.guides.springboot.notificationsystem.dto.GetPushTokenDto;
import net.guides.springboot.notificationsystem.model.PushToken;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-18T19:54:27+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class PushTokenAdapterMapperImpl implements PushTokenAdapterMapper {

    @Override
    public PushToken getEntity(CreatePushTokenDto dto) {
        if ( dto == null ) {
            return null;
        }

        PushToken pushToken = new PushToken();

        pushToken.setDeviceId( dto.getDeviceId() );
        pushToken.setToken( dto.getToken() );
        pushToken.setType( dto.getType() );

        return pushToken;
    }

    @Override
    public CreatePushTokenDto getDto(PushToken entity) {
        if ( entity == null ) {
            return null;
        }

        CreatePushTokenDto createPushTokenDto = new CreatePushTokenDto();

        createPushTokenDto.setDeviceId( entity.getDeviceId() );
        createPushTokenDto.setToken( entity.getToken() );
        createPushTokenDto.setType( entity.getType() );

        return createPushTokenDto;
    }

    @Override
    public GetPushTokenDto pushTokenToGetPushTokenDto(PushToken pushToken) {
        if ( pushToken == null ) {
            return null;
        }

        GetPushTokenDto getPushTokenDto = new GetPushTokenDto();

        getPushTokenDto.setUserId( pushToken.getUserId() );
        getPushTokenDto.setDeviceId( pushToken.getDeviceId() );
        getPushTokenDto.setToken( pushToken.getToken() );
        getPushTokenDto.setType( pushToken.getType() );

        return getPushTokenDto;
    }
}
