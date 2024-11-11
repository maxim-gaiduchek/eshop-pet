import {Flex} from "antd";
import {Footer} from "antd/lib/layout/layout";
import {Link} from "react-router-dom"
import {githubRepositoryUrl, linkedInUrl, yearOfStart} from "../../../config";
import {GithubOutlined, LinkedinOutlined} from "@ant-design/icons";
import {secondaryBackgroundColor} from "../../../colors";

export function ShopFooter({footerHeight}) {
    const now = new Date().getFullYear();
    let year = yearOfStart.toString();
    if (yearOfStart !== now) {
        year += "-" + now;
    }
    return (
        <Footer style={{
            width: "100%",
            height: footerHeight ? footerHeight : 100,
            backgroundColor: secondaryBackgroundColor,
            display: "flex",
            justifyContent: "center"
        }}>
            <Flex style={{
                alignItems: "center",
                flexDirection: "row-reverse",
                width: "100%"
            }}>
                <p>&copy; {year} Maksym Gaiduchek</p>
                <Link to={githubRepositoryUrl}>
                    <GithubOutlined style={{padding: 10}}/>
                </Link>
                <Link to={linkedInUrl}>
                    <LinkedinOutlined style={{padding: 10}}/>
                </Link>
            </Flex>
        </Footer>
    )
}
