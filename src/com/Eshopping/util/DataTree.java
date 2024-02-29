package com.Eshopping.util;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class DataTree {
	protected String idField = "id";
    protected String pidField = "pid";
    protected String nodesField = "children";

    protected String rootId = "-1";
    protected String leafField = "isLeaf";
    protected String levelField = "_level";
    protected String expandedField = "expanded";
 
    protected ArrayList tree = new ArrayList();     //树形数据
    protected ArrayList list = new ArrayList();     //列表数据
    protected ArrayList dataview = null;            //数据视图：折�?

    protected HashMap idMaps = new HashMap();

    public DataTree(String idField, String pidField, String nodesField)
    {
        this.idField = idField;
        this.pidField = pidField;
        this.nodesField = nodesField;
    }
    /// <summary>
    /// 加载列表数据
    /// </summary>
    /// <param name="list"></param>
    public void LoadList(ArrayList list)
    {
        if (list == null) list = new ArrayList();
        ArrayList tree = TreeHelper.list2Tree(list, nodesField, idField, pidField);
        Load(tree);
    }
    /// <summary>
    /// 加载树形数据
    /// </summary>
    /// <param name="tree"></param>
    public void Load(ArrayList tree)
    {
        //节点必须有idField
        if (tree == null) tree = new ArrayList();
        list = TreeHelper.tree2List(tree, "-1", nodesField, idField, pidField);
        dataview = null;

        //idField存储哈希，便于快速检�?
        idMaps = new HashMap();
        for (int i = list.size() - 1; i >= 0; i--)
        {
            HashMap node = (HashMap)list.get(i);
            idMaps.put(node.get(idField).toString(), node);
        }

        //遍历列表，生成leafField, levelField
        for (int i = list.size() - 1; i >= 0; i--)
        {
            HashMap node = (HashMap)list.get(i);
            String id = node.get(idField).toString();
            ArrayList nodes = (ArrayList)node.get(nodesField);
            node.put(leafField, (nodes == null || nodes.size() == 0) ? true : false);
            node.put(levelField, GetAncestors(id).size());
        }

        //清除折叠信息
        _collapseNodes = new ArrayList();
        DoExpandeds();

        //清除过滤信息
        filtered = null;
    }        
    public int GetTotalCount()
    {
        return GetDataView().size();
    }
    public ArrayList GetPagedData(int pageIndex, int pageSize)
    {
        //1)折叠后的数据视图
        ArrayList list = GetDataView();

        //2)返回分页数据
        int pages = list.size() / pageSize;
        if (pages * pageSize < list.size()) pages += 1;

        if (pageIndex > pages - 1) pageIndex = pages - 1;

        ArrayList nodes = new ArrayList();
        int start = pageIndex * pageSize;
        int end = (pageIndex + 1) * pageSize;

        for (int i = start; i < end; i++)
        {
            if (i > list.size() - 1 || i < 0) continue;
            HashMap node = (HashMap)list.get(i);
            if (node == null)
            {
                continue;
            }
            nodes.add(node);
        }

        return CloneNodes(nodes);            
    }

    //////////////////////////////////////////////////////////////////////////////////
    protected ArrayList _collapseNodes = new ArrayList();
    public void SetRequest(HttpServletRequest request)
    {
    	String s = request.getParameter("__ecconfig");
        if (!StringUtil.isNullOrEmpty(s))
        {
            HashMap config = (HashMap)JSON.decode(s);
            _collapseNodes = (ArrayList)config.get("collapseNodes");

            if (_collapseNodes == null) _collapseNodes = new ArrayList();
        }
        DoExpandeds();
        dataview = null;
    }
    protected void DoExpandeds()
    {
        //处理expandedField
        for (int i = 0, l = list.size(); i < l; i++)
        {
            HashMap node = (HashMap)list.get(i);
            node.remove(expandedField);
        }
        for (int i = 0, l = _collapseNodes.size(); i < l; i++)
        {
            String id = _collapseNodes.get(i).toString();
            HashMap node = GetNode(id);
            node.put(expandedField, false);
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
    /// <summary>
    /// 获取数据视图：过滤�?�折叠后
    /// </summary>
    /// <returns></returns>
    public ArrayList GetDataView()
    {
        if (dataview == null)
        {                
            //expanded
            ArrayList data = new ArrayList();
            for (int i = 0,l=list.size(); i<l; i++)
            {
                HashMap node = (HashMap)list.get(i);
                if (IsVisibleNode(node))
                {
                    data.add(node);
                }
            }

            //filter
            if (filtered != null)
            {
                //1)缓存过滤节点和父节点
                HashMap filterMaps = new HashMap();
                for (int i = 0, l = filtered.size(); i < l; i++)
                {
                    HashMap node = (HashMap)filtered.get(i);
                    String id = node.get(idField).toString();
                    if (filterMaps.get(id) == null) filterMaps.put(id, node);

                    ArrayList ans = GetAncestors(id);
                    for (int j = 0, k = ans.size(); j < k; j++)
                    {
                        HashMap pnode = (HashMap)ans.get(j);
                        String pid = pnode.get(idField).toString();
                        if (filterMaps.get(pid) == null) filterMaps.put(pid, pnode);
                    }
                }
                //2)data删除�?有不存在filterMaps中的节点
                for (int i = data.size() - 1; i >= 0; i--)
                {
                    HashMap node = (HashMap)data.get(i);
                    String id = node.get(idField).toString();
                    if (filterMaps.get(id) == null)
                    {
                        data.remove(i);
                    }
                }

            }
                                
            dataview = data;
        }
        return dataview;
    }
    protected ArrayList filtered = null;
    /// <summary>
    /// 设置过滤后的节点数组
    /// </summary>
    /// <param name="nodes"></param>       
    public void SetFiltered(ArrayList nodes)
    {
        filtered = nodes;
        dataview = null;
    }
    //////////////////////////////////////////////////////////////////////////////////
    public ArrayList GetAncestors(String id)
    {
        ArrayList ans = new ArrayList();
        while (true)
        {
            HashMap parentNode = GetParentNode(id);
            if (parentNode == null) break;
            ans.add(parentNode);
            id = parentNode.get(pidField).toString();
        }        
        reverseArray(ans);
        return ans;
    }        
    public HashMap GetParentNode(String pid)
    {
        return (HashMap)idMaps.get(pid);
    }
    public ArrayList GetChildNodes(String id)
    {
        HashMap node = (HashMap)idMaps.get(id);
        if (node == null) return null;
        return (ArrayList)node.get(nodesField);
    }
    public HashMap GetNode(String id)
    {
        return (HashMap)idMaps.get(id);
    }
    protected Boolean IsVisibleNode(HashMap node)
    {
        HashMap parent = GetParentNode(node.get(pidField).toString());
        if (parent == null) return true;
        if (parent.get(expandedField) == null) return IsVisibleNode(parent);
        if ((Boolean)parent.get(expandedField) == false) return false;
        return true;
    }
    protected ArrayList CloneNodes(ArrayList nodes)
    {
        ArrayList clone = (ArrayList)JSON.decode(JSON.encode(nodes));
        for (int i = 0, l = clone.size(); i < l; i++)
        {
            HashMap node = (HashMap)clone.get(i);
            node.remove(nodesField);
        }
        return clone;
    }
    protected static void reverseArray(List b) {
  	   int left  = 0;          // index of leftmost element
  	   int right = b.size()-1; // index of rightmost element
  	 
  	   while (left < right) {
  	      // exchange the left and right elements
  	      Object temp = b.get(left); 
  	      b.set(left, b.get(right)); 
  	      b.set(right, temp);  
  	      // move the bounds toward the center
  	      left++;
  	      right--;
  	   }
  	}
}
