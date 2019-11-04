import flask
from werkzeug import secure_filename
import os
import img_caption
import base64

app = flask.Flask("Image_Captioning")

@app.route('/', methods=['GET'])
def hello_world():
    return "Hello World"

@app.route('/caption', methods = ['POST'])
def captioning():
    f = flask.request.files['img']
    f.save(secure_filename("image_to_caption.jpg"))
    # return 'file uploaded successfully'
    caption = img_caption.caption_image("image_to_caption.jpg")
    return caption


@app.route('/caption_b64', methods = ['POST'])
def captioning_b64():
    content = flask.request.get_json()
    with open("b64.txt", 'w') as f:
        f.write(content["b64string"])
    with open("image_to_caption.jpg", "wb") as f:
        f.write(base64.decodebytes(bytes(content["b64string"].replace('\n', ''), "utf-8")))
    caption = img_caption.caption_image("image_to_caption.jpg")
    return caption

@app.route('/test', methods = ['POST'])
def test():
    content = flask.request.get_json()
    print(content)
    return flask.jsonify({"success": True, "content": content})


if __name__ == '__main__':
    app.run(host='0.0.0.0')
