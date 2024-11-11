import {Link} from "react-router-dom";
import {Button, Flex} from "antd";

export function LoginRegisterButtons() {
    return (
        <Flex>
            <Link to={"/login"}>
                <Button style={{margin: "5px"}}>Login</Button>
            </Link>
            <Link to={"/register"}>
                <Button style={{margin: "5px"}}>Register</Button>
            </Link>
        </Flex>
    )
}
