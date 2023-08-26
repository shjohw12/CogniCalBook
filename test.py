# import sys
# file_path = sys.argv[1]

# input_file = open(file_path, 'r')
output_file = open('output.txt', 'w')

# while True:
# 	line = input_file.readline()
# 	if not line:
# 		break
# 	print(line)
# 	output_file.write(line)

# input_file.close()

import base64

with open("./sample.png", "rb") as f:
    img = base64.b64encode(f.read())

output_file.write(img.decode('utf-8'))
output_file.close()