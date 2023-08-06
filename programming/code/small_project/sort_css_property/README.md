# Sort CSS Property

Sort the properties in css in a certain order.

## Usage

Command Format:

```bash
> python3 sort.py css_file_name
```

Example:

1.  Execute `python3 sort.py style-test.css`.
1.  The program will generate `style-test-processed.css`.

```css
/* style-test.css */
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

/* style-test-processed.css */
.button {
  display: inline-block;
  padding: 15px 32px;
  color: white;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  background-color: #4CAF50;
  border: none;
}
```
