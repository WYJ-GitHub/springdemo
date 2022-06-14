package springdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCredentialsEntity extends CommonEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
	 *  用户名或手机号等
	 */
	@ApiModelProperty(value = "用户名或手机号等", name = "username")
    private String username;
    /**
	 *  账号类型（用户名、手机号）
	 */
	@ApiModelProperty(value = "账号类型（用户名、手机号）", name = "type")
    private String type;
    /**
	 *  用户id
	 */
	@ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;


}
