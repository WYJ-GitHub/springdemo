package springdemo.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import springdemo.core.annotation.ExternalField;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户名", name = "userName")
	private String userName;

	@ApiModelProperty(value = "密码", name = "password")
	private String password;

    @ApiModelProperty(value = "前台鉴权使用 token 单点登录")
    @ExternalField
    private String token;
}
