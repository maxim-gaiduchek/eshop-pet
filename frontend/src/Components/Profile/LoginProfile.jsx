import {LoginRegisterButtons} from "./Components/LoginRegisterButtons";
import {LoginUserProfile} from "./Components/LoginUserProfile";

export function LoginProfile() {
    let loginUserId = localStorage.getItem("loginUserId");
    if (loginUserId) {
        return (
            <LoginUserProfile/>
        )
    }
    return (
        <LoginRegisterButtons/>
    )
}
