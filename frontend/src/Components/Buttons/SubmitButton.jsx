export function SubmitButton({disabled, value, style}) {
    return (
        <button type="submit" className={"submitButton"} value={value} disabled={disabled}
                style={{...style}}>{value}</button>
    )
}
