package net.guides.springboot.notificationsystem.controller;



import com.vasl.ario.crudutil.repository.RepositoryUtils;
import com.vasl.ario.crudutil.service.model.PageQueryParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import net.guides.springboot.notificationsystem.adapter.mapper.PushTokenAdapterMapper;
import net.guides.springboot.notificationsystem.dto.CreatePushTokenDto;
import net.guides.springboot.notificationsystem.dto.GetPushTokenDto;
import net.guides.springboot.notificationsystem.service.PushTokenService;
import net.guides.springboot.notificationsystem.service.Utils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("notifications/push/tokens")
public class PushTokenController {


    private final PushTokenService pushTokenService;

    private final PushTokenAdapterMapper mapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasAnyAuthority('notification.push.token.create')")
    public void save(@RequestBody @Valid CreatePushTokenDto pushTokenDto) {
        pushTokenService.save(
                pushTokenDto.getDeviceId(),
                pushTokenDto.getToken(),
                pushTokenDto.getType(),
                Utils.getUserIdFromContext());
    }

    /**
     * Get all.
     *
     *
     * @return the list of tokens
     */
    @GetMapping
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNumber", example = "0", value = "starts from 0"),
            @ApiImplicitParam(name = "pageSize", example = "10", value = "must be greater than 0"),
            @ApiImplicitParam(name = "sort", example = "ASC", value = "ASC=ascending ,DESC=descending"),
            @ApiImplicitParam(name = "sortKey", example = "[key1,key2]", type = "List", allowMultiple = true, value = "sort will be based on these words")
    })
    @PreAuthorize(value = "hasAnyAuthority('notification.push.token.get.all')")
    public Page<GetPushTokenDto> getAll(@Validated PageQueryParams pageQueryParams) {
        return pushTokenService
                .getPageByPageable(RepositoryUtils.getPageableFromPageQueryParams(pageQueryParams))
                .map(mapper::pushTokenToGetPushTokenDto);
    }

    /**
     * Get push token.
     *
     * @param id the id
     * @return the push token dto
     */
    @GetMapping("/{push-id}")
    @PreAuthorize(value = "hasAnyAuthority('notification.push.token.get')")
    public GetPushTokenDto get(@PathVariable("push-id") Long id) {
        return mapper.pushTokenToGetPushTokenDto(pushTokenService.getByPushId(id));
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("/{push-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(value = "hasAnyAuthority('notification.push.token.delete')")
    public void delete (@PathVariable("push-id") Long id) {
        pushTokenService.deleteByPushId(id);
    }

}
