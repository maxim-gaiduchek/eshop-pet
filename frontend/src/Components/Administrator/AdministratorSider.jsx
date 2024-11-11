import {secondaryBackgroundColor} from "../../colors";
import {Link} from "react-router-dom";
import {Button, Flex} from "antd";
import Sider from "antd/lib/layout/Sider";

export function AdministratorSider() {
    return (
        <Sider style={{
            height: "100%",
            backgroundColor: secondaryBackgroundColor,
            overflowY: "hidden",
            alignItems: "center",
        }}>
            <Flex style={{
                alignItems: "center",
                flexDirection: "column",
                width: "90%",
                margin: "0 auto"
            }}>
                <Link to={"/sellers"} style={{width: "100%", margin: "10px 0"}}>
                    <Button style={{width: "100%"}}>Sellers</Button>
                </Link>
                <Link to={"/companies"} style={{width: "100%", margin: "10px 0"}}>
                    <Button style={{width: "100%"}}>Companies</Button>
                </Link>
            </Flex>
        </Sider>
    )
}
