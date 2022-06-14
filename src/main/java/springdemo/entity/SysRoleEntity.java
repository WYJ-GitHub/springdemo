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
public class SysRoleEntity extends CommonEntity implements Serializable {

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
	 *  角色code
	 */
	@ApiModelProperty(value = "角色code", name = "code")
    private String code;
    /**
	 *  角色名
	 */
	@ApiModelProperty(value = "角色名", name = "name")
    private String name;
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
