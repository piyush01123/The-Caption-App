
cd app/
wget https://transfer.sh/12YRJ4/encoder_weights.h5
wget https://transfer.sh/3IbLl/tokenizer.pickle
wget https://transfer.sh/quAGH/decoder_layers.tar.gz
wget https://transfer.sh/JANRG/encoder_layers.tar.gz
tar xvzf encoder_layers.tar.gz
tar xvzf decoder_layers.tar.gz
python3 -m virtualenv venv
source venv/bin/activate
pip install -r requirements.txt
python app.py

# or alternatively
# docker run -d -p 5000:5000 piyushkgp/image_caption
