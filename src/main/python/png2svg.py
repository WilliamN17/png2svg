from pixels2svg import pixels2svg


def transcode(input, output) -> int:
    pixels2svg(input, output_path=output, as_string=True)
    return 1


