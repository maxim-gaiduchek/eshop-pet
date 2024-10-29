export function CenteredLayout(props) {
    return (
        <div style={{
            width: "100%",
            maxWidth: 500,
            margin: "100px auto"
        }}>
            {props.children}
        </div>
    )
}
