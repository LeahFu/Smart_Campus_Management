package ca.access.student.vo;

/**
 * @author: Lei Fu
 * @date: 2023/07/06
 * @description: Modify password model
 */
public class ModifyPwdModel {
    /**
     * Old password
     */
    private String usedPass;

    /**
     * New password
     */
    private String newPass;

    /**
     * login user ID
     */
    private Long userId;

    public String getUsedPass() {
        return usedPass;
    }

    public void setUsedPass(String usedPass) {
        this.usedPass = usedPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
