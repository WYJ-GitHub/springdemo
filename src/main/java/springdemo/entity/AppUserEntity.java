package springdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.util.Date;

@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUserEntity extends CommonEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
	 *  自增id
	 */
	@ApiModelProperty(value = "自增id", name = "id")
    private Integer id;
    /**
	 *  用户名
	 */
	@ApiModelProperty(value = "用户名", name = "username")
    private String username;
    /**
	 *  密码
	 */
	@ApiModelProperty(value = "密码", name = "password")
    private String password;
    /**
	 *  昵称
	 */
	@ApiModelProperty(value = "昵称", name = "nickname")
    private String nickname;
    /**
	 *  头像url
	 */
	@ApiModelProperty(value = "头像url", name = "headImgUrl")
    private String headImgUrl;
    /**
	 *  手机号
	 */
	@ApiModelProperty(value = "手机号", name = "phone")
    private String phone;
    /**
	 *  性别
	 */
	@ApiModelProperty(value = "性别", name = "sex")
    private Integer sex;
    /**
	 *  状态（1有效,0无效）
	 */
	@ApiModelProperty(value = "状态（1有效,0无效）", name = "enabled")
    private Integer enabled;
    /**
	 *  类型（暂未用）
	 */
	@ApiModelProperty(value = "类型（暂未用）", name = "type")
    private String type;
    /**
	 *  创建时间
	 */
	@ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    /**
	 *  修改时间
	 */
	@ApiModelProperty(value = "修改时间", name = "updateTime")
    private Date updateTime;


}
