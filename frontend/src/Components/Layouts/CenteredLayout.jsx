import {Flex} from "antd";

export function CenteredLayout(props) {
    return (
        <Flex style={{
            flexDirection: "column",
            alignItems: "center",
            width: "100%",
            maxWidth: 500,
            margin: "100px auto"
        }}>
            {props.children}
        </Flex>
    )
}
