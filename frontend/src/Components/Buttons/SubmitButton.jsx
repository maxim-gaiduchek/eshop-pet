export function SubmitButton({disabled, value}) {
    return (
        <button type="submit" className={"submitButton"} value={value} disabled={disabled}>{value}</button>
    )
}
