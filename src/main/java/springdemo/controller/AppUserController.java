package springdemo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springdemo.service.AppUserService;
import springdemo.core.annotation.LoginRequired;
import springdemo.core.annotation.RecordLog;
import springdemo.entity.PageData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import springdemo.entity.AppUserEntity;
import java.util.List;

@RestController
@Api(tags = "appUser接口")
@RequestMapping("/appUser")
public class AppUserController {


	private final AppUserService service;

	@Autowired
	public AppUserController(AppUserService service) {
		this.service = service;
	}

	/**
	 * 查询
	 *
	 * @return
	 */
	@ApiOperation(value = "查询")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/select")
	public List<AppUserEntity> select(@RequestBody AppUserEntity entity) {
		return service.select(entity);
	}

	/**
	 * 模糊查询
	 *
	 * @return
	 */
	@ApiOperation(value = "模糊查询")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/likeSelect")
	public PageData<AppUserEntity> likeSelect(@RequestBody AppUserEntity entity) {
		return service.likeSelect(entity);
	}

	/**
	 * 更新
	 *
	 * @return
	 */
	@ApiOperation(value = "更新")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/update")
	public void update(@RequestBody AppUserEntity entity) {
		service.update(entity);
	}

	/**
	 * 添加
	 *
	 * @return
	 */
	@ApiOperation(value = "添加")
    @LoginRequired
	@RecordLog
	@PostMapping(value = "/add")
	public void add(@RequestBody AppUserEntity entity) {
		service.add(entity);
	}

	/**
	 * 删除
	 *
	 * @return
	 */
	@ApiOperation(value = "删除")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/delete")
	public void delete(@RequestBody AppUserEntity entity) {
		service.delete(entity);
	}

	/**
	 * 导出excel
	 *
	 * @return
	 */
	@ApiOperation(value = "导出excel")
	@LoginRequired
	@RecordLog
	@GetMapping("/exportExcel")
	public void exportExcel(AppUserEntity entity, HttpServletResponse response) {
		service.exportExcel(entity, response);
	}

}
