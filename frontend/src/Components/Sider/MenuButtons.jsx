import {Button, Flex} from "antd";
import {Link} from "react-router-dom";

export function MenuButtons() {
    const role = localStorage.getItem("loginUserRole");
    return (
        <Flex style={{
            alignItems: "center",
            flexDirection: "column",
            width: "90%",
            margin: "0 auto"
        }}>
            {
                role && role === "ROLE_ADMINISTRATOR" ?
                    <>
                        <Link to={"/settings"} style={{width: "100%", margin: "10px 0"}}>
                            <Button style={{width: "100%"}}>Settings</Button>
                        </Link>
                        <Link to={"/sellers"} style={{width: "100%", margin: "10px 0"}}>
                            <Button style={{width: "100%"}}>Sellers</Button>
                        </Link>
                    </> : <></>
            }
            {
                role && Array.of("ROLE_SELLER", "ROLE_ADMINISTRATOR").includes(role) ?
                    <>
                        <Link to={"/companies"} style={{width: "100%", margin: "10px 0"}}>
                            <Button style={{width: "100%"}}>Companies</Button>
                        </Link>
                    </> : <></>
            }
        </Flex>
    )
}
