class Solution(object):
    
    
    def simplifyPath(self, path):
        """
        :type path: str
        :rtype: str
        """
        path += "/"
        directories = []
        folder_name = ""
        for i, ch in enumerate(path):
            if(ch == "/"):
                if folder_name != "":
                    if folder_name == ".":
                        pass
                    elif folder_name == "..":
                        if len(directories) > 0:
                            directories.pop()  
                    else:
                        directories.append(folder_name)
                    folder_name = ""
            else:
                folder_name += ch
                
                
        if len(directories) == 0:
            simplified_path = "/"
        else:
            simplified_path = ""
            for i, name in enumerate(directories):
                simplified_path += "/"
                simplified_path += name
        return simplified_path
        
